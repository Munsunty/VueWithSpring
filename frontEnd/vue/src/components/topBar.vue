<template>
  <div id="topBar" class="center-left">
    <img alt="Vue logo" src="@/assets/logo.png">
    <div id="buttonArea-left" class="buttonArea left">
      <starButton></starButton>
      <plusButton></plusButton>
      <topButton></topButton>
      <bottomButton></bottomButton>
      <saveButton></saveButton>
    </div>
    <div id="buttonArea-right" class="buttonArea right">

    </div>
  </div>
</template>

<script>
import topButton from '@/components/button/topButton'
import bottomButton from '@/components/button/bottomButton'
import starButton from '@/components/button/starButton'
import plusButton from "@/components/button/plusButton"
import saveButton from "@/components/button/saveButton"

export default {
  name: 'topBar',
  props:{
    checked:Boolean
  },
  components: {
    topButton,
    bottomButton,
    starButton,
    plusButton,
    saveButton
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
  width: 50%;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

</style>
