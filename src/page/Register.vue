<template>
    <van-form  @submit="onSubmit">
  <van-field
    v-for="(v,index) in registerInfo" :key="index" 
    v-model="infoList[index]"
    :name="v"
    :label="v"  
    :placeholder="v"
    :rules="[{ required: true, message: `'请填写${v}'` }]"
  />
    <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit">提交</van-button>
    </div>
    </van-form>
</template>

<script>
import { reactive } from "vue";
import { useRouter} from 'vue-router'
import {registerPassenger,registerDriver,registerCar} from '../request/Api'
export default {
    name:'Register',
    props:['registerInfo','registerKeys'],
    setup(props){
    const $router = useRouter()//vue3中使用编程式路由
    let  infoList = reactive([...""])//用于配合v-for遍历input的输入框
    
    function mapPeople(values) {
      return {
        name:values.姓名,
        sex: values.性别,
        age: parseInt(values.年龄),
        identityID: parseInt(values.身份证号),
        account: parseInt(values.账号),
        password: values.密码
      }
    }

    //跳转到登录界面
    function toLogin(){
        alert("注册成功!")
         $router.push({
                name:'denglu'
            })
    }

    function mapCar(values){
        return {
           color : values.车辆颜色,
           type : values.车型,
           code : values.车辆编号,
           age : parseInt(values.车龄) 
        }
    }


     function onSubmit(values) {

       if(props.registerKeys == '乘客注册'){
            values = mapPeople(values)
            registerPassenger({
            values
            }).then(res=>{ 
              toLogin()
      })
    }
      else if(props.registerKeys == '驾驶员注册'){
          values = mapPeople(values)
          registerDriver({
               values   
          }).then(res=>{
            toLogin()
          })
      }

      else if(props.registerKeys == '车辆注册'){
          values = mapCar(values)
         registerCar({
            values
         }) .then(res=>{
             toLogin()
         })
      }  
    }
      return {infoList,onSubmit}
    }
}
</script>

<style>

</style>