import { createApp } from 'vue'
import App from './App.vue'
import mitt from 'mitt' //vue全局事件总线库
import router from './router'
import { Button, Image, Tabbar, TabbarItem } from "vant";
import { Form } from 'vant';
import { Field } from 'vant';
import { Icon } from 'vant';

const app = createApp(App)
app.config.globalProperties.$mybus = new mitt()
app.use(router)
   .use(Button)
   .use(Image) 
   .use(Tabbar) 
   .use(TabbarItem)
   .use(Form)
   .use(Field)
   .use(Icon)
   .mount('#app') 
      




