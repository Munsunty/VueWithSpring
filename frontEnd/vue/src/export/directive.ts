module.exports = (Vue:any) => {
    Vue.directive('auto-scroll-bottom', {
        update: (el:any) => {
            el.scrollTop = el.scrollHeight
        }
    })
}