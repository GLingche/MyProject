<template>
  <div id="app" class="wrap" >

  <Banner v-show="!isAdmin"  v-on:click="MyShow"></Banner>

  <AdminBanner v-show="isAdmin" v-on:click="MyShow" ></AdminBanner> 

      <router-view >

      </router-view>
  
   <van-tabbar route>
        <van-tabbar-item replace to="/" icon="search" >应用</van-tabbar-item>
         <van-tabbar-item replace to="/home" icon="home-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
import Banner from "./components/Banner";
import AdminBanner from "./components/AdminBanner"
import {ref,toRef} from 'vue'
export default {
  name: "App",
  components: {
    Banner,
    AdminBanner
  },
  setup(){
    let isAdmin = ref(false)

    function test(info){
        console.log("111")
        console.log(info)
    }

    //自定义触发事件引用函数
    function MyShow(){
      console.log("@@####")
    }

      //改变登录状态
      function changeStatus(status) {
        console.log(isAdmin.value)
        isAdmin.value = status
    }

    return {test,MyShow,isAdmin,changeStatus}
  },
  mounted(){
       this.$mybus.on('test',this.test)
       this.$mybus.on('changeBanner',this.changeStatus)
    }  
};
</script>
    


<style scoped>
    .wrap{
         margin:0 auto;
         width:1000px;
         height:1000px;
         background-color:#487eb0;
    }
    .MyView{
         margin:0 auto;
         width:1000px;
         height:400px;
    }
</style>
