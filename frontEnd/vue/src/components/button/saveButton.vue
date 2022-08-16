<template>
  <div >
    <img @click="fn" src="@/assets/2x/sharp_save_white_48dp.png" style="cursor: pointer;" />
  </div>
</template>
<script>
import {sendDataPostAxios} from "@/export/exfortFunction";
import {cy} from '@/export/exfortVar';

export default {
  name: 'saveButton',
  methods:{
    fn(){
      const jsonData =cy.json().elements;
      let nodes=[];
      let edges=[];
      console.log(jsonData);
      if(jsonData.nodes.length>0&&jsonData.edges.length>0){
        jsonData.nodes.forEach(node=>{
          nodes.push(node.data)
        });
        jsonData.edges.forEach(edge=>{
          edges.push(edge.data)
        });
        let url="save"
        let params = {
          mode:'ben',
          nodes: nodes,
          edges:edges
        };
        let success = function (response){
          console.log(response);
        };
        return sendDataPostAxios(url,params,success);
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
img{
  width: 48px;
  height: 48px;
}
</style>
