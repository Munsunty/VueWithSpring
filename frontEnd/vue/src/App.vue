<template>
  <form-box v-if="checked&&!edit"></form-box>
  <input id="statusSwitch" type="checkbox"  v-model="checked" style="display: none"/>
  <span style="float: left; position: absolute;top: 10%; left:10%; font-size: 3em;" v-if="edit">---EDIT MODE---</span>
  <input id="editSwitch" type="checkbox" v-model="edit" style="display: none"/>
  <input id="leftBarSwitch" type="checkbox" v-model="turnOnLBar" style="display: none"/>
  <topBar></topBar>
  <leftBar v-show="turnOnLBar"></leftBar>
  <input id="gridSwitch" type="checkbox" v-model="grid" style="display: none"/>
  <myComponent v-if="grid" ></myComponent>
  <container v-show="!grid"></container>
</template>

<script>
import topBar from './components/topBar.vue'
import leftBar from './components/leftBar.vue'
import container from './components/container/diagramContainer.vue'
import formBox from './components/container/formContainer.vue'
import MyComponent from "./components/table/MyComponent"
import {addCy} from "@/export/exfortVar";
import {getDataByAxios} from "@/export/exfortFunction";

export default {
  name: 'App',
  created(){
    let url="search"
    let params = {
      mode:'search',
    };
    let success = function (response){
      let list = response.data;
      if(Array.isArray(list)){
        list.forEach(item=>{
          addCy(item);
        })
      }
    };
    return getDataByAxios(url,params,success);
  },
  data(){
    return{
      checked:false,
      edit:false,
      turnOnLBar:false,
      grid:false
    }
  },
  components: {
    topBar,
    leftBar,
    container,
    formBox,
    MyComponent
  },
  setup(){

  }
}


</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.center-left{
  position: absolute;
  left: 50%;
  transform: translate(-50%,0);
}
.center-Common{
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
}
#registration{
  border: solid 1px lightgray;
  top: 10%;
  position: absolute;
  right: 3%;
  padding: 10px 10px 10px 10px;
  max-width: 400px;
  z-index: 100;
}


</style>
