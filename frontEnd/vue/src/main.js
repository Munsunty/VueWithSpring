import { createApp } from 'vue'
import App from './App.vue'
import { plugin, defaultConfig } from '@formkit/vue'
defaultConfig({
    theme: 'genesis' // will load from CDN and inject into document head
})
createApp(App).use(plugin, defaultConfig).mount('#app');
