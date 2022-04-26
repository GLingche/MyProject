<template>
  <div class="m_top">
    <h2><van-icon class-prefix="my-icon" name="extra" />灵车出行</h2>
    <ul>
      <li>
        <span v-text="`欢迎您&nbsp${adminName.name}`"></span>
        <van-icon class-prefix="my-icon2" name="extra" />
        <button class="leave" @click="leave">退出</button>
      </li>

      <li>
        <select @click="extend" class="banner">
          <option v-for="(v, index) in range" :key="index" :value="v">
            {{ v }}
          </option>
        </select>
      </li>
      <li>
        <select @click="exportInfo" class="banner">
          <option v-for="(v, index) in record" :key="index" :value="v">
            {{ v }}
          </option>
        </select>
      </li>
    </ul>
  </div>
</template>

<script>
import { getCurrentInstance, reactive } from "vue";
import {
  adminUnLogin,
  exportPassengerInfo,
  exportDriverInfo,
} from "../request/Api";
export default {
  props: ["isAdmin"],
  name: "AdminBanner",
  emits: ["click"], //vue3声明自定义click事件，覆盖默认行为，native被弃用
  setup(props) {
    const { proxy } = getCurrentInstance(); //获取当前vue对象的代理实例
    let range = reactive(["扩大范围", "缩短范围"]);
    let record = reactive(["导出乘客信息", "导出司机信息"]);
    let adminName = reactive({ name: null });
    let adminInfo = reactive({ values: null });

    const leave = () => {
      let values = adminInfo.values;

      adminUnLogin({
        values,
      }).then((res) => {
        console.log(res);
      });
      proxy.$mybus.emit("leave", props.isAdmin.toString());
    };

    const extend = (value) => {
      proxy.$mybus.emit("mapExtend", value.target.value);
    };

    const exportInfo = (value) => {
      value.target.value == "导出乘客信息"
        ? exportPassengerInfo()
        : exportDriverInfo();
    };

    return { range, record, extend, leave, adminName, adminInfo, exportInfo };
  },
  mounted() {
    //获取管理员的信息
    this.$mybus.on("searchAdminInfo", (value) => {
      this.adminName.name = value.name;
      this.adminInfo.values = value;
    });
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
.leave {
  background: rgba(255, 255, 255, 0.2);
  border: none;
}

/* 自定义Icon 1 */
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

/* 自定义Icon 2*/
@font-face {
  font-family: "my-icon2"; /* Project id  */
  src: url("../assets/incontff/iconfont.ttf") format("truetype");
}

.my-icon2 {
  font-family: "my-icon2" !important;
  color: black;
  font-size: 45px;
  font-style: normal;
  padding-right: 10px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.my-icon2-extra::before {
  content: "\f012d";
}
</style>
