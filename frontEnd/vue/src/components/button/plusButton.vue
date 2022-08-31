<template>
  <div >
    <img @click="fn" src="@/assets/2x/sharp_add_white_48dp.png" style="cursor: pointer;" />
  </div>
</template>
<script>
import {turnForm} from  '@/export/exfortFunction'

export default {
  name: 'plusButton',
  methods:{
    fn(){
      turnForm();
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
