export let data=[];

export let cy;

export let formShow=false;


export function changeData(item){
    data=item;
}
export function setCy(item){
    cy=item;
    cy.zoom({
        level: 1.5
    });
    cy.resize();
}
export function changeCy(item){
    console.log(item);
}

export function addCy(item,){
    cy.add({
        group: 'nodes',
        data: {
            id: item.name+'-'+item.type+'-'+item.version, info:item
        },
        position: {
            x: 100, y: 100
        }
    });

}

