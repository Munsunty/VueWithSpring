<template>
  <div >
    <img @click="fn" src="@/assets/2x/baseline_star_white_48dp.png" style="cursor: pointer;" />
  </div>
</template>
<script>


export default {
  name: 'starButton',
  methods:{
    fn(){

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
