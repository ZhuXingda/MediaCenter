function move_up(scrollTop){
  if(scrollTop>0){
    scrollTop=Math.max(scrollTop-500,0)
    setTimeout(()=>{
      document.documentElement.scrollTop=scrollTop
      move_up(scrollTop)
    },5)
  }
}

export default {
  install (Vue, options){
    Vue.prototype.backUp = function (){
      document.getElementById('back-up-button').blur()
      let scrollTop = document.documentElement.scrollTop;
      move_up(scrollTop)
    }
  }
}

