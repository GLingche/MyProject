import { createRouter, createWebHashHistory } from 'vue-router'

//导入组件
import start from '../page/Start.vue'
import home from '../page/Home.vue'
import login from '../page/Login.vue'
import register from '../page/Register.vue'
import resume from '../page/Resume.vue'

//创建路由
const Router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            // 设置默认界面
            name: 'chufa',
            path: '/',
            component: start,
            props: true,
            children:[
                {
                  name:'jieshao',
                  path:'resume',
                  component:resume,//这里component没有s
                  props:true      
                },     
            ]
        },
        {
            path: '/home',
            component: home,
            children: [
                {
                    name: 'denglu',
                    path: '',
                    component: login,
                    props: true
                },
                {
                    name: 'zhuce',
                    path: 'register',
                    component: register,
                    props: true
                }
            ]
        }
    ]
})

export default Router