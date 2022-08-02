<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <button @click="fn">click</button>
    <button @click="fn2">click</button>
  </div>
</template>

<script>
import {getDataByAxios,sendDataPostAxios} from "@/export/exfortFunction";

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  methods:{
    fn(){
      let url="search"
      let params = {
        mode:'search',
      };
      let success = function (response){
        console.log(response);
      };
      return getDataByAxios(url,params,success);
    },
    fn2(){
      let url="search"
      let params = {
        mode:'search',
      };
      let success = function (response){
        console.log(response);
      };
      return sendDataPostAxios(url,params,success);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
