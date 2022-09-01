<template>
  <div id="table-box">

    <div id="tableGrid">
      <div class="tableGrid left">
        <div id="table-search-area">
          <select v-model="searchField">
            <option value="key">{{header_Name.f1}}</option>
            <option value="value">{{header_Name.f2}}</option>
          </select>
          <span>검색: </span>
          <input type="text" v-model="searchValue">
        </div>
        <div id="searchList">
          <EasyDataTable
              v-model:items-selected="itemsSelected"
              show-index
              buttons-pagination
              :headers="headers"
              :items="items"
              :search-field="searchField"
              :search-value="searchValue"
              alternating
              :rows-per-page="20"
              :rows-items="[10,20,50,100]"
          >

            <template #item-operation="item">
              <div class="operation-wrapper">
                <img
                    src="@/assets/2x/round_arrow_circle_down_black_48dp.png"
                    class="operation-icon"
                    @click="addRow(item)"
                />
              </div>
            </template>
          </EasyDataTable>
        </div>
      </div>
      <div class="tableGrid center">
      </div>
      <div class="tableGrid right">
        <resultTable :headers="headers" :result="result"  :key="componentKey" :set="resultSet" @delRow="delRow"></resultTable>
      </div>
    </div>
  </div>

</template>

<script lang="ts" setup>
import type { Header, Item, ClickRowArgument  } from "vue3-easy-data-table";
import { ref } from "vue";
const searchField = ref("key");
const searchValue = ref("");
const itemsSelected = ref<Item[]>([]);

import {cy} from "@/export/exfortVar";
import resultTable from "./resultTable"

const header_Name={
  f1:'영역',
  f2:'언어'
}

const componentKey = ref(0);

const forceRerender = () => {
  componentKey.value += 1;
};

const result: Item[] = [];
let resultSet=new Set();
const delRow = (item: ClickRowArgument) =>{
  resultSet.delete(item.id);
  let cnt = -1 ;

  result.forEach((row,idx)=>{
    console.log(row.id==item.id)
    if(row.id==item.id){
      cnt=idx;
    }
  });

  result.splice(cnt,1);
  forceRerender();
}

const addRow = (item: ClickRowArgument) => {
  if(!resultSet.has(item.id)){
    resultSet.add(item.id);
    result.push(item);
    forceRerender();
  }
};
const headers: Header[] = [
  { text: header_Name.f1, value: "key", sortable: true },
  { text: header_Name.f2, value: "value", sortable: true },
  { text: "작동", value: "operation" },

];
const jsonData:any =cy.json().elements.nodes;
let nodes:any =[];
if(typeof jsonData != 'undefined'){
  jsonData.forEach( (item:any)=>{
    // eslint-disable-next-line no-prototype-builtins
    if(item.data.hasOwnProperty('info')){
      nodes.push({key:item.data.info.key,value:item.data.info.value, id:item.data.id});
    }
  })
}

const items : Item[] =  nodes;
</script>

<style>
#table-box{
  position: absolute;
  top: 10%;
  width: 100%;
  height: 90%;
}

#tableGrid{
  display: flex;
  height: 100%;
}
.tableGrid.left{
  width: 70%;
}
.tableGrid.center{
  width: 5%;
}
.tableGrid.right{
  width: 25%;
  padding-right: 20px;
}
.operation-icon{
  width: 20px;
  cursor: pointer;
}
#table-search-area{
  height: 5%;
}
#searchList{
  height: 95%;
  overflow-y: auto;
}
</style>
