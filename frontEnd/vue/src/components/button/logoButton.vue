<template>
  <div >
    <img @click="fn" alt="Vue logo" src="@/assets/2x/baseline_forest_white_48dp.png" style="cursor: pointer;">
  </div>
</template>
<script>
import {getDataByAxios, turnLeftBar} from "@/export/exfortFunction";

export default {
  name: 'logoButton',
  methods:{
    fn(){
      let checked = turnLeftBar();
      // eslint-disable-next-line no-empty
      if(checked){

      }
      let url="search"
      let params = {
        mode:'search',
      };
      let success = function (response){
        console.log(response);
      };
      return getDataByAxios(url,params,success);
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
