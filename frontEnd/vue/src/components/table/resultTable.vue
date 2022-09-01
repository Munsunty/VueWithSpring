<template>
        <EasyDataTable
            show-index
            :headers="resultHeaders"
            :items="resultItems"
            alternating
        >
          <template #item-operation="item">
            <div class="operation-wrapper">
              <img
                  src="@/assets/2x/outline_delete_black_48dp.png"
                  class="operation-icon"
                  @click="$emit('delRow',item)"
              />
            </div>
          </template>

        </EasyDataTable>

</template>

<script lang="ts">

import {defineComponent, ref, onUpdated} from "vue";
import {Item} from "vue3-easy-data-table";



export default defineComponent({
  props: {
    headers: Array,
    result:Array,
    resultSet:Set
  },

  setup(props:any) {
    let resultItems= ref<Item[]>(props.result);
    let resultHeaders = ref<Item[]>(props.headers);

    onUpdated(() => {
      console.log('updated!')
    })

    const test1=(() => {
      console.log('updated!')
    })
    const test2=(() => {
      console.log('updated!')
    })


    return {
      resultHeaders,
      resultItems,
      test1,
      test2

    }
  }
});
</script>

<style>

</style>
