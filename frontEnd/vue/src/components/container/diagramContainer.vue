<template>
  <div id="cy-wrapper">
    <div id="cy" ref="cyElement"></div>
  </div>
</template>
<script>
import { defineComponent, nextTick, onMounted, ref } from 'vue'
import {setCy,cy} from '@/export/exfortVar';


export default defineComponent({
  name: 'diagramContainer',
  setup() {
    const cyElement = ref(null);
    const cyInstance = ref(null);

    const resizeGraph = () => {
      if (cyInstance.value) {
        cyInstance.value.resize();
      }
    }

    const test = async () => {
      await nextTick()
      resizeGraph();
    }

    onMounted(() => {
      import('cytoscape')
          .then(() => {
            const element = document.createElement('div');
            element.setAttribute('id', 'cy-mounting-point');
            cyElement.value.appendChild(element);

            setCy(element);
            cyInstance.value = Object.freeze(
                cy
            );

            test();
          })
    });

    return {
      cyElement,
    }
  },
})
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#cy-wrapper{
  position: absolute;
  top: 10%;
  width: 100%;
  height: 90%;
}
#cy {
  width: 100%;
  height: 100%;
}

</style>
