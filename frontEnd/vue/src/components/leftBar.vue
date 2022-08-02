<template>
  <div id="leftBar" >
    <div id="buttonArea-up" class="buttonArea">
      menu
    </div>
    <div id="buttonArea-down" class="buttonArea">
      menu2
    </div>
  </div>
</template>

<script>


export default {
  name: 'leftBar',
  props: {
    msg: String
  },
  components: {

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#leftBar {
  position: absolute;
  width: 5%;
  top: 10%;
  height: 90%;
  background: rgba(255, 147, 0, 0.58);
  display: flex;
  flex-direction: column;
}
.buttonArea{
  height: 50%;
  width: 100%;
  display: flex;

  justify-content: space-around;
}

</style>
