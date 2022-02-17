<template>
    <div class="login">
             <div class="form_head">
        <h3 v-text="title || initTitle"></h3>
        </div>

    <form @submit="onSubmit">
        <div class="form_input">
            <span>usename:</span>
            <input class="sign-text" type="text" v-model="values.account" name="usename" placeholder="请输入用户名">
        </div>

        <div class="form_input">
            <span> password:</span>
            <input class="sign-text" type="text" v-model="values.password" name="password" placeholder="请输入密码">
        </div>

        <div class="form_input">
            <input class="sign-btn" type="submit" name="sumbit" value="login"> 
        </div>
    </form>

    </div>
   
</template>

<script>
import axios from 'axios'
import { useRouter} from 'vue-router'
import {getCurrentInstance,ref,reactive} from 'vue'
import {adminLogin,passengerLogin,driverLogin} from '../request/Api'
export default {
    name:'Login',
    props:['title'],
    setup(props){
        const $router = useRouter()//vue3中使用编程式路由
        let initTitle = "乘客登录"
        let values = reactive({
            account:null,
            password:null
        })

        let loginStatus = false
        let Admin = reactive({name:"admin"},{status:false})
        let Passenger = reactive({name:"passenger"},{status:false})
        let Driver = reactive({name:'driver'},{status:false})

        const {proxy} = getCurrentInstance();//获取当前vue对象的代理实例

        function ChangeBanner(info) {
            proxy.$mybus.emit('changeBanner',info)
        }

        function onSubmit(){
            values.account = parseInt(values.account)  
            if(props.title=="管理员登录"){
                 ChangeBanner(Admin)
                 adminLogin({
                     values
                 }).then(res=>{
                     alert(res.msg)
                     proxy.$mybus.emit('searchAdminInfo',res.values)
                 })
            }
            /*
             由于默认值initTitle不随title的值改变,这里加多一层if判断
             防止每次切换头部导航栏时都会保留乘客登录的导航栏
            */
           else if(props.title=="乘客登录" || initTitle=="乘客登录"){
                if(props.title == "驾驶员登录"){
                driverLogin({
                     values
                 }).then(res=>{
                     alert(res.msg)
                     proxy.$mybus.emit('searchDriverInfo',res.values)
                 })
                    ChangeBanner(Driver)
                    $router.push({
                            name:'chufa',
                            params:{
                            loginStatus
                        }
                    })
                    return
                }
                   passengerLogin({
                     values
                 }).then(res=>{
                     alert(res.msg)
                     proxy.$mybus.emit('searchPassengerInfo',res.values)
                 })
                ChangeBanner(Passenger)
            }
            $router.push({
                name:'chufa',
                params:{
                    loginStatus
                }
            })
        }

        return {onSubmit,initTitle,values,Admin,Passenger}
    }   
}
</script>

<style scoped>
.login{
    position: absolute;
    left:35%;
    top:25%;
    width: 600px;
    padding:20px;
    background: rgba(255, 255, 255, 0.2);
    border-radius: 35px;
    /*对齐*/
    display: flex;
    flex-direction: column;
}
.login input{
    padding:0 16px;
    height:44px;
    outline:none;
    border:none;
    font-family:"montserrat",sans-serif;
}

.form_input span {
    width: 100px;
    align-self: center;
}
.form_head{
    padding-top:1px;
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

.sign-btn{
    width:100%;
    margin-top:35px;
    margin-bottom: 20px;
    background:#f40;
    color:#fff;
    border-radius:44px;
    cursor:pointer;         /*当鼠标移动到login按钮时变成手的图标*/
    transition: 0.8s;       /*变幻的时间*/
}

.sign-btn:hover{
    transform: scale(0.7);  /*效果：当鼠标移动到login按钮时改按钮会缩小，参数为变幻系数*/
}

</style>