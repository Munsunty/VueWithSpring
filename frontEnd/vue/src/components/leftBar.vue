<template>
  <div id="leftBar" >

  </div>
</template>

<script>

export default {
  name: 'leftBar',
  props: {
    msg: String
  },
  setup() {

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
  box-shadow: 0 6px 6px rgb(0 0 0 / 20%);
  display: flex;
  flex-direction: column;
}
.buttonArea{
  height: 50%;
  width: 100%;
  display: flex;
  justify-content: space-around;
  flex-direction: column;
}

</style>
