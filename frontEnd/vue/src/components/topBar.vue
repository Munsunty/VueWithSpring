<template>
  <div id="topBar" class="center-left">
    <div id="buttonArea-logo" class="logoArea">
      <logo-button></logo-button>
    </div>
    <div id="buttonArea-left" class="buttonArea left">
      <starButton></starButton>
      <saveButton></saveButton>
    </div>
    <div id="buttonArea-right" class="buttonArea right">
      <topButton></topButton>
      <plusButton></plusButton>
    </div>
  </div>
</template>

<script>

import starButton from '@/components/button/starButton';
import LogoButton from "@/components/button/logoButton";
import plusButton from "@/components/button/plusButton";
import saveButton from "@/components/button/saveButton";
import topButton from "@/components/button/topButton";


export default {
  name: 'topBar',
  props:{
    checked:Boolean
  },
  components: {
    LogoButton,
    starButton,
    plusButton,
    saveButton,
    topButton
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#topBar {
  width: 100%;
  height: 10%;
  background: cornflowerblue;
  display: flex;
}
.buttonArea{
  height: 100%;
  width: 45%;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
.logoArea{
  height: 100%;
  width: 10%;
  display: flex;
  align-items: center;
  justify-content: space-around;
}
</style>
