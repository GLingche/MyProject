import { createRouter, createWebHashHistory } from "vue-router";

//导入组件
import start from "../page/Starter.vue";
import home from "../page/Home.vue";
import login from "../page/Login.vue";
import register from "../page/Register.vue";
import resume from "../page/Resume.vue";

//创建路由
const Router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      // 设置默认界面
      name: "chufa",
      path: "/",
      component: start,
      props: true,
      meta: { isAuth: true, title: "出发" },
      children: [
        {
          name: "jieshao",
          path: "resume",
          component: resume, //这里component没有s
          props: true,
        },
      ],
    },
    {
      path: "/login",
      component: home,
      children: [
        {
          name: "denglu",
          path: "",
          component: login,
          props: true,
        },
        {
          name: "zhuce",
          path: "register",
          component: register,
          props: true,
        },
      ],
    },
  ],
});

//前置路由
Router.beforeEach((to, from, next) => {
  //首先判断该路由是否有权限
  if (to.meta.isAuth) {
    if (localStorage.getItem("passenger")) {
      next();
    } else {
      next("/login");
      alert("请先登录哦！！");
    }
  } else {
    next();
  }
});

export default Router;
