<template>
  <div id="app" class="wrap" >
    
  <Passenger v-show="isPassenger" isPassenger="isPassenger"></Passenger>
  <Banner v-show="!isAdmin && !isPassenger"   v-on:click="MyShow"></Banner>

  <AdminBanner v-show="isAdmin" isAdmin="isAdmin"  v-on:click="MyShow" ></AdminBanner> 

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
import Passenger from "./components/Passenger"
import {ref} from 'vue'
export default {
  name: "App",
  components: {
    Banner,
    AdminBanner,
    Passenger
  },
  setup(){
    let isAdmin = ref(false)
    let isPassenger  = ref(false)

    function test(info){
        console.log("111")
        console.log(info)
    }

    //自定义触发事件引用函数
    function MyShow(){
      console.log("@@####")
    }

      //改变登录状态
      function changeStatus(info) {
        if(info.name == "admin") {
          isAdmin.value = !info.status
        }else{
          isPassenger.value = !info.status
        }
    }

    //退出登录
    function leave(info){
      if(info=="isPassenger"){
        isPassenger.value = false
      }else{ 
        isAdmin.value = false
      }
    }

    return {test,MyShow,isAdmin,changeStatus,isPassenger,leave}
  },
  mounted(){
       this.$mybus.on('test',this.test)
       this.$mybus.on('changeBanner',this.changeStatus)
       this.$mybus.on('leave',this.leave)
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
