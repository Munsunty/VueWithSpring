import { createApp } from 'vue'
import App from './App.vue'
import { plugin, defaultConfig } from '@formkit/vue'

import Vue3EasyDataTable from 'vue3-easy-data-table';
import 'vue3-easy-data-table/dist/style.css';

defaultConfig({
    theme: 'genesis' // will load from CDN and inject into document head
})
createApp(App)
    .component('EasyDataTable', Vue3EasyDataTable)
    .use( plugin, defaultConfig)
    .mount('#app');
