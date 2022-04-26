<template>
  <div class="m_top">
    <h2><van-icon class-prefix="my-icon" name="extra" />灵车出行</h2>
    <ul>
      <li>
        <select @click="loginShow" class="banner">
          <option v-for="(v, index) in loginKeys" :key="index" :value="v">
            {{ v }}
          </option>
        </select>
      </li>
      <li>
        <select @click="registerShow" class="banner">
          <option v-for="(v, index) in registerKeys" :key="index" :value="v">
            {{ v }}
          </option>
        </select>
      </li>
      <li>
        <select>
          <option value="">广州</option>
          <option value="">上海</option>
        </select>
      </li>
      <li>行程</li>
    </ul>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { getCurrentInstance, reactive } from "vue";

export default {
  name: "Banner",
  emits: ["click"], //vue3声明自定义click事件，覆盖默认行为，native被弃用
  setup() {
    console.log("Banner1111111111");
    const loginKeys = reactive(["乘客登录", "驾驶员登录", "管理员登录"]);
    const registerKeys = reactive(["乘客注册", "驾驶员注册", "车辆注册"]);
    const userInfo = reactive([
      ["姓名", "性别", "年龄", "身份证号", "账号", "密码"],
      ["姓名", "性别", "年龄", "身份证号", "账号", "密码"],
      ["车辆颜色", "车型", "车辆编号", "车龄"],
    ]);

    const $router = useRouter(); //vue3中使用编程式路由
    // const $route = useRoute()
    const { proxy } = getCurrentInstance(); //获取当前vue对象的代理实例

    function test() {
      proxy.$mybus.emit("test", "info22");
    }

    //登录界面展示
    const loginShow = (value) => {
      $router.push({
        name: "denglu",
        params: {
          title: value.target.value,
        },
      });
    };

    //注册界面展示
    const registerShow = (value) => {
      if (value.target.value == "乘客注册") {
        $router.push({
          name: "zhuce",
          params: {
            registerInfo: userInfo[0],
            registerKeys: registerKeys[0],
          },
        });
      } else if (value.target.value == "车辆注册") {
        $router.push({
          name: "zhuce",
          params: {
            registerInfo: userInfo[2],
            registerKeys: registerKeys[2],
          },
        });
      } else {
        $router.push({
          name: "zhuce",
          params: {
            registerInfo: userInfo[1],
            registerKeys: registerKeys[1],
          },
        });
      }
    };

    return { loginShow, registerShow, test, loginKeys, registerKeys, userInfo };
  },
};
</script>

<style scoped>
.m_top {
  height: 70px;
  width: 100%;
  border-bottom: 0.5px solid gray;
  background: rgba(255, 255, 255, 0.2);
}
.m_view {
  position: absulote;
  height: 600px;
}
h2 {
  padding-right: 3px;
  float: left;
  font-family: "Times New Roman", Times, serif;
  font-style: italic;
  font-size: 40px;
  line-height: 40px;
}
li {
  font-size: 30px;
  float: right;
  padding: 0 19px;
  line-height: 70px;
  color: #000;
}
li .banner:hover {
  cursor: pointer;
  background: #000;
  text-decoration: none;
  color: #ddd;
}
select {
  background: rgba(255, 255, 255, 0.2);
  border: none;
}
option {
  background: rgba(255, 255, 255, 0.2);
}

/* 自定义Icon */
@font-face {
  font-family: "my-icon";
  src: url("../assets/iconfont.ttf") format("truetype");
}

.my-icon {
  font-family: "my-icon" !important;
  color: black;
  font-size: 45px;
  font-style: italic;
  padding-right: 10px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.my-icon-extra::before {
  content: "\e694";
}
</style>