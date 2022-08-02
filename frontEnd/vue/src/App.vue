<template>
  <topBar></topBar>
  <leftBar></leftBar>
  <container></container>
</template>

<script>
import topBar from './components/topBar.vue'
import leftBar from './components/leftBar.vue'
import container from './components/container/diagramContainer.vue'



export default {
  name: 'App',
  components: {
    topBar,
    leftBar,
    container
  }
}


</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.center-left{
  position: absolute;
  left: 50%;
  transform: translate(-50%,0);
}
.center-Common{
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
}

</style>
