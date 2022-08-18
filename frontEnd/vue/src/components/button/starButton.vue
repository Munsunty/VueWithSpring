<template>
  <div >
    <img @click="fn" src="@/assets/2x/baseline_star_white_48dp.png" style="cursor: pointer;" />
  </div>
</template>
<script>
import {getDataByAxios} from "@/export/exfortFunction";
import {addCy} from "@/export/exfortVar";

export default {
  name: 'starButton',
  methods:{
    fn(){
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
