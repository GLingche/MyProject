<template>
  <div class="login">
    <div class="form_head">
      <h3 v-text="title || initTitle"></h3>
    </div>
    <form @submit.prevent="onSubmit" :model="values">
      <div class="form_input">
        <span>usename:</span>
        <input
          class="sign-text"
          type="text"
          v-model="values.account"
          name="usename"
          placeholder="请输入用户名"
        />
      </div>
      <div class="form_input">
        <span> password:</span>
        <input
          class="sign-text"
          type="text"
          v-model="values.password"
          name="password"
          placeholder="请输入密码"
        />
      </div>
      <div class="form_input">
        <input class="sign-btn" type="submit" name="sumbit" value="login" />
      </div>
    </form>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
import { getCurrentInstance, reactive } from "vue";
import { adminLogin, passengerLogin, driverLogin } from "../request/Api";
import { Toast } from "vant";
export default {
  name: "Login",
  props: ["title"],
  setup(props) {
    const $router = useRouter(); //vue3中使用编程式路由
    let initTitle = "乘客登录";
    let values = reactive({
      account: null,
      password: null,
    });

    let loginStatus = false;
    let Admin = reactive({ name: "admin" }, { status: false });
    let Passenger = reactive({ name: "passenger" }, { status: false });
    let Driver = reactive({ name: "driver" }, { status: false });

    const { proxy } = getCurrentInstance(); //获取当前vue对象的代理实例

    function ChangeBanner(info) {
      proxy.$mybus.emit("changeBanner", info);
    }

    // 校验账号密码的合法性
    function valid() {
      //账号格式5-12位数字
      let Pattern = /^\d{5,12}$/;
      console.log(values.account);
      if (
        values.account == null ||
        values.account == "" ||
        values.password == null ||
        values.password == ""
      ) {
        Toast({
          message: "账号或密码不能为空",
          duration: 1500,
          forbidClick: true,
          iconSize: 50,
        });
        return false;
      } else if (
        !Pattern.test(values.account) ||
        !Pattern.test(values.password)
      ) {
        Toast({
          message: "账号或密码应为5-12数字",
          duration: 1500,
          forbidClick: true,
          iconSize: 50,
        });
        return false;
      } else {
        return true;
      }
    }

    //判断用户的权限
    function checkauth() {
      values.account = parseInt(values.account);
      if (props.title == "管理员登录") {
        adminLogin({
          values,
        }).then((res) => {
          alert(res.msg);
          if (res.code == 5000) {
            ChangeBanner(Admin);
            proxy.$mybus.emit("searchAdminInfo", res.values);
            $router.push({
              name: "chufa",
              params: {
                loginStatus,
              },
            });
          }
        });
      } else if (props.title == "乘客登录" || initTitle == "乘客登录") {
        /*
             由于默认值initTitle不随title的值改变,这里加多一层if判断
             防止每次切换头部导航栏时都会保留乘客登录的导航栏
            */
        if (props.title == "驾驶员登录") {
          driverLogin({
            values,
          }).then((res) => {
            alert(res.msg);
            if (res.code == 5000) {
              ChangeBanner(Driver);
              proxy.$mybus.emit("searchDriverInfo", res.values);
              $router.push({
                name: "chufa",
                params: {
                  loginStatus,
                },
              });
            }
          });
          return;
        }
        passengerLogin({
          values,
        }).then((res) => {
          let info = JSON.stringify(res);
          //在乘客信息保存在本地浏览器中
          localStorage.setItem("passenger", info);
          alert(res.msg);
          if (res.code == 5000) {
            ChangeBanner(Passenger);
            proxy.$mybus.emit("searchPassengerInfo", res.values);
            loginStatus = res.values.status;
            $router.push({
              name: "chufa",
              params: {
                loginStatus,
              },
            });
          }
        });
      }
    }

    function onSubmit() {
      if (valid()) {
        checkauth();
      }
    }

    return { onSubmit, initTitle, values, Admin, Passenger };
  },
};
</script>

<style scoped>
.login {
  width: 600px;
  padding: 20px;
  margin: 200px auto;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 35px;
  /*对齐*/
  display: flex;
  flex-direction: column;
}
.login input {
  padding: 0 16px;
  height: 44px;
  outline: none;
  border: none;
  font-family: "montserrat", sans-serif;
}

.form_input span {
  width: 100px;
  align-self: center;
}
.form_head {
  padding-top: 1px;
  text-align: center;
}
.form_input {
  padding: 0 50px;
  box-sizing: border-box;
  display: flex; /*横向对齐由于默认值是row，所以不用写direction*/
  margin-top: 10px;
}

.sign-text {
  width: 55%;
  margin: 4px;
  background: rgba(255, 255, 255, 5);
  border-radius: 6px;
}

.sign-btn {
  width: 100%;
  margin-top: 35px;
  margin-bottom: 20px;
  background: #f40;
  color: #fff;
  border-radius: 44px;
  cursor: pointer; /*当鼠标移动到login按钮时变成手的图标*/
  transition: 0.8s; /*变幻的时间*/
}

.sign-btn:hover {
  transform: scale(
    0.7
  ); /*效果：当鼠标移动到login按钮时改按钮会缩小，参数为变幻系数*/
}
</style>
