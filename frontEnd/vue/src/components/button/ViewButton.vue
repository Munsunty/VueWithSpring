<template>
  <div >
    <img @click="fn" src="@/assets/2x/baseline_view_list_white_48dp.png" style="cursor: pointer;" />
  </div>
</template>
<script>


import {turnGrid} from "@/export/exfortFunction";

export default {
  name: 'ViewButton',
  methods:{
    fn(){
      turnGrid();
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
