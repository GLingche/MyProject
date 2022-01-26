<template>
  <div id="app" class="wrap" >
  <Banner  v-on:click="MyShow"></Banner>
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
export default {
  name: "App",
  components: {
    Banner,
  },
  setup(){

    function test(info){
        console.log("111")
        console.log(info)
    }

    function MyShow(){
      console.log("@@####")
    }

    return {test,MyShow}
  },
  mounted(){
       this.$mybus.on('test',this.test)
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
