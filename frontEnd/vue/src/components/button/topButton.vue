<template>
  <div >
    <img @click="fn" src="@/assets/2x/baseline_vertical_align_top_white_48dp.png" style="cursor: pointer;" />
  </div>
</template>
<script>
import {cy} from "@/export/exfortVar";
export default {
  name: 'topButton',
  props:{

  },
  methods:{
    fn() {
      let r = { group: 'edges', data: { id: 'e0', source: '내용 요소:소인수분해', target: '핵심개념:수의 체계' } };
      cy.add(r);
    }
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
img{
  width: 48px;
  height: 48px;
}
</style>
