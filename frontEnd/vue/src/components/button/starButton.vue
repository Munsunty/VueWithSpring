<template>
  <div >
    <img @click="fn" src="@/assets/2x/baseline_star_white_48dp.png" style="cursor: pointer;" />
  </div>
</template>
<script>
import {getDataByAxios} from "@/export/exfortFunction";
import {cy} from "@/export/exfortVar";

export default {
  name: 'starButton',
  methods:{
    fn(){
      let url="search"
      let params = {
        mode:'search',
      };
      let success = function (response){
        let list = response.data;
        list.forEach((item,idx)=>{
          let info = item.info;
          // eslint-disable-next-line no-prototype-builtins
          if(info.hasOwnProperty('key')&&info.hasOwnProperty('value')){
            item.data={
              id:info.key+":"+info.value,
              info:info
            };
            item.position={
              x:idx%2==1?(1+idx)*100:0,
              y:idx%2==0?0:(1+idx)*50
            }
          }
        });
        cy.add(list);
        /*
        let nodeMap = new Map();
        let beforeMap = new Map();
        const depth = 80;
        const width = 115;
        let arr=[];
        if(Array.isArray(list)&&list.length>0){
          list[0].position={x:600,y:40};
          list.forEach((item)=>{
            if(item.group=='nodes'){
              nodeMap.set(item.data.id,item);
            }else{
              arr.push(item);
            }
          });

          arr.forEach(item=>{
            var parent = nodeMap.get(item.data.source);
            var child = nodeMap.get(item.data.target);
            var pPosition = parent.position;
            if(beforeMap.has(parent)){
              var cnt = beforeMap.get(parent);
              if(cnt%2==1){
                child.position={x:pPosition.x+width*cnt, y:pPosition.y+depth}
              }else {
                child.position={x:pPosition.x-width*(cnt-1), y:pPosition.y+depth}
              }
              beforeMap.set(parent,cnt+1);
            }else{
              child.position={x:pPosition.x, y:pPosition.y+depth}
              beforeMap.set(parent,1);
            }
          })

          cy.add(list);
        }

         */
      };
      return getDataByAxios(url,params,success);
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
