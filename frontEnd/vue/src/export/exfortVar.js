import cytoscape from 'cytoscape';
import edgehandles from 'cytoscape-edgehandles';
import compoundDragAndDrop from 'cytoscape-compound-drag-and-drop';

export let data=[
    { group: 'nodes', data: { id: 'n0' }, position: { x: 100, y: 100 } },
    { group: 'nodes', data: { id: 'n1' }, position: { x: 200, y: 200 } },
    { group: 'edges', data: { id: 'e0', source: 'n0', target: 'n1' } }
];

export let cy;

export let eh;

export let cdnd;

export let formShow=false;


export function changeData(item){
    data=item;
}


export function setCy(element){
    cytoscape.use( edgehandles );
    cytoscape.use( compoundDragAndDrop );
    cy=cytoscape({
        container: element,
        elements: [],
        style: [ // the stylesheet for the graph
            {
                selector: 'node',
                style: {
                    'background-color': '#00ab62',
                    'label': 'data(id)'
                }
            },
            {
                selector: 'node:parent',
                style: {
                    'label': '',
                    'background-color': 'lightgray'
                }
            },
            {
                selector: 'edge',
                style: {
                    'width': 3,
                    'line-color': '#00fff7',
                    'target-arrow-color': '#00ffff',
                    'target-arrow-shape': 'triangle',
                    'curve-style': 'bezier'
                }
            },

            {
                selector: '.eh-handle',
                style: {
                    'background-color': 'red',
                    'width': 12,
                    'height': 12,
                    'shape': 'ellipse',
                    'overlay-opacity': 0,
                    'border-width': 12, // makes the handle easier to hit
                    'border-opacity': 0
                }
            },

            {
                selector: '.eh-hover',
                style: {
                    'background-color': 'red'
                }
            },

            {
                selector: '.eh-source',
                style: {
                    'border-width': 2,
                    'border-color': 'red'
                }
            },

            {
                selector: '.eh-target',
                style: {
                    'border-width': 2,
                    'border-color': 'red'
                }
            },

            {
                selector: '.eh-preview, .eh-ghost-edge',
                style: {
                    'background-color': 'red',
                    'line-color': 'red',
                    'target-arrow-color': 'red',
                    'source-arrow-color': 'red'
                }
            },

            {
                selector: '.eh-ghost-edge.eh-preview-active',
                style: {
                    'opacity': 0
                }
            },

            {
                selector: '.cdnd-grabbed-node',
                style: {
                    'background-color': 'red'
                }
            },

            {
                selector: '.cdnd-drop-sibling',
                style: {
                    'background-color': 'red'
                }
            },

            {
                selector: '.cdnd-drop-target',
                style: {
                    'border-color': 'red',
                    'border-style': 'dashed'
                }
            },
        ],
        layout: {
            name: 'grid',
            rows: 1
        }
    });

    cy.reset();
    cy.zoom({
        level: 1, // the zoom level
        position: { x: 0, y: 0 }
    });
    cy.resize();
    cy.center();
    addEdgehandles();



    let cnt = 0;
    let targetSet = new Set();
    let editBt = document.getElementById('editSwitch');
    cy.on('click', 'edge', function(evt){
        var edge = evt.target;
        if(editBt.checked){
            if(targetSet.has(edge.id)){
                cnt++;
                if(cnt>2){
                    cy.remove(edge);
                    targetSet.clear();
                    cnt=0;
                }
            }else{
                targetSet.clear();
                cnt=0;
                targetSet.add(edge.id)
            }
        }
    });
    cy.on('click', 'node', function(evt){
        var node = evt.target;
        if(editBt.checked){
            if(targetSet.has(node.id)){
                cnt++;
                if(cnt>2){
                    cy.remove(node);
                    targetSet.clear();
                    cnt=0;
                }
            }else{
                targetSet.clear();
                cnt=0;
                targetSet.add(node.id)
            }
        }
    });

    window.addEventListener('keydown',()=>{
        if(event.key=='Insert'){
            editBt.click();
            if(editBt.checked){
                eh.enableDrawMode();
                cdnd.disable(); // disables the UI

            }else{
                eh.disableDrawMode();
                cdnd.enable(); // re-enables the UI

            }

        }
    });

}

function addEdgehandles(){
    let defaults = {
        canConnect: function( sourceNode, targetNode ){
            // whether an edge can be created between source and target
            return !sourceNode.same(targetNode); // e.g. disallow loops
        },
        edgeParams: function( sourceNode, targetNode ){
            console.log(sourceNode);
            console.log(targetNode);
            // for edges between the specified source and target
            // return element object to be passed to cy.add() for edge
            return {};
        },
        hoverDelay: 150, // time spent hovering over a target node before it is considered selected
        snap: true, // when enabled, the edge can be drawn by just moving close to a target node (can be confusing on compound graphs)
        snapThreshold: 50, // the target node must be less than or equal to this many pixels away from the cursor/finger
        snapFrequency: 15, // the number of times per second (Hz) that snap checks done (lower is less expensive)
        noEdgeEventsInDraw: true, // set events:no to edges during draws, prevents mouseouts on compounds
        disableBrowserGestures: true // during an edge drawing gesture, disable browser gestures such as two-finger trackpad swipe and pinch-to-zoom
    };
    eh = cy.edgehandles(defaults);

    cdnd = cy.compoundDragAndDrop();

}
export function changeCy(item){
    console.log(item);
}

export function addCy(item,){
    cy.add({
        group: 'nodes',
        data: {
            id: item.key+":"+item.value, info:item
        },
        position: {
            x: 100, y: 100
        }
    });

}

