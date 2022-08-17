<template>
  <form-box v-if="checked&&!edit"></form-box>
<!--  <form-box v-if="checked&&edit"></form-box>-->
  <input id="statusSwitch" type="checkbox"  v-model="checked" style="display: none"/>
  <span style="float: left; position: absolute;top: 10%; left:10%; font-size: 3em;" v-if="edit">---EDIT MODE---</span>
  <input id="editSwitch" type="checkbox" v-model="edit" style="display: none"/>
  <input id="leftBarSwitch" type="checkbox" v-model="turnOnLBar" style="display: none"/>
  <topBar></topBar>
  <leftBar v-show="turnOnLBar"></leftBar>
  <container></container>
</template>

<script>
import topBar from './components/topBar.vue'
import leftBar from './components/leftBar.vue'
import container from './components/container/diagramContainer.vue'
import formBox from './components/container/formContainer.vue'


export default {
  name: 'App',
  data(){
    return{
      checked:false,
      edit:false,
      turnOnLBar:false
    }
  },
  components: {
    topBar,
    leftBar,
    container,
    formBox
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
