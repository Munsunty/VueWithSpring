<template>
  <div id="cy-wrapper">
    <div id="cy" ref="cyElement"></div>
  </div>
</template>
<script>
import { defineComponent, nextTick, onMounted, ref } from 'vue'
import cytoscape from 'cytoscape';
import {setCy,data,cy} from '@/export/exfortVar'


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

            setCy(cytoscape({
              container: element,
              elements: data,

              style: [ // the stylesheet for the graph
                {
                  selector: 'node',
                  style: {
                    'background-color': '#666',
                    'label': 'data(id)'
                  }
                },

                {
                  selector: 'edge',
                  style: {
                    'width': 3,
                    'line-color': '#ccc',
                    'target-arrow-color': '#ccc',
                    'target-arrow-shape': 'triangle',
                    'curve-style': 'bezier'
                  }
                }
              ],
              layout: {
                name: 'grid',
                rows: 1
              }
            }));

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
  left: 5%;
  width: 95%;
  height: 90%;
}
#cy {
  width: 100%;
  height: 100%;
}

</style>
