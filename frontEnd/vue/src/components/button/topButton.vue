<template>
  <div >
    <img @click="fn" src="@/assets/2x/baseline_vertical_align_top_white_48dp.png" style="cursor: pointer;" />
  </div>
</template>
<script>
import {getDataByAxios} from "@/export/exfortFunction";

export default {
  name: 'topButton',
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
