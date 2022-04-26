<template>
  <div id="app" class="wrap">
    <DriverBanner v-show="isDriver" isDriver="isDriver"></DriverBanner>

    <PassengerBanner
      v-show="isPassenger"
      isPassenger="isPassenger"
    ></PassengerBanner>

    <Banner
      v-show="!isAdmin && !isPassenger && !isDriver"
      v-on:click="MyShow"
    ></Banner>

    <AdminBanner
      v-show="isAdmin"
      isAdmin="isAdmin"
      v-on:click="MyShow"
    ></AdminBanner>
    <!-- 配置了两个多级路由   ！！！注解不能写在router-view标签里面！！！ -->
    <router-view> </router-view>
    <van-tabbar route>
      <van-tabbar-item replace to="/" icon="search">应用</van-tabbar-item>
      <van-tabbar-item replace to="/login" icon="home-o">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
import Banner from "./components/Banner";
import AdminBanner from "./components/AdminBanner";
import PassengerBanner from "./components/PassengerBanner";
import DriverBanner from "./components/DriverBanner";
import { ref } from "vue";
import { useRouter } from "vue-router";
export default {
  name: "App",
  components: {
    Banner,
    AdminBanner,
    PassengerBanner,
    DriverBanner,
  },
  setup() {
    const $router = useRouter(); //vue3中使用编程式路由
    let isAdmin = ref(false);
    let isPassenger = ref(false);
    let isDriver = ref(false);

    function test(info) {
      console.log("111");
      console.log(info);
    }

    //自定义触发事件引用函数
    function MyShow() {
      console.log("@@####");
    }

    //改变登录状态
    function changeStatus(info) {
      if (info.name == "driver") {
        isDriver.value = !info.status;
      } else if (info.name == "admin") {
        isAdmin.value = !info.status;
      } else {
        isPassenger.value = !info.status;
      }
    }

    //退出登录
    function leave(info) {
      if (info == "isDriver") {
        isDriver.value = false;
      } else if (info == "isPassenger") {
        isPassenger.value = false;
      } else {
        isAdmin.value = false;
      }

      $router.push({
        name: "denglu",
      });
    }

    return {
      test,
      MyShow,
      isAdmin,
      changeStatus,
      isPassenger,
      leave,
      isDriver,
    };
  },
  mounted() {
    this.$mybus.on("test", this.test);
    this.$mybus.on("changeBanner", this.changeStatus);
    this.$mybus.on("leave", this.leave);
  },
};
</script>
    


<style scoped>
.wrap {
  margin: 0 auto;
  width: 1000px;
  height: 1000px;
  background-color: #487eb0;
}
</style>
