/**   
 * api接口统一管理
 */
import { get, post } from './Http'

//查询地址
export const searchAddress = () => get('/admin/searchAddress');



//乘客注册
export const registerPassenger = p => post('/passenger/registerInfo',p) 

//驾驶员注册
export const registerDriver = p => post('/driver/registerInfo',p)

//车辆注册
export const registerCar = p => post('/car/registerInfo',p)



//管理员登录
export const adminLogin = p => post('/admin/login',p)
//管理员退出登录
export const adminUnLogin = p => post('/admin/unLogin',p)

//乘客登录
export const passengerLogin = p => post('/passenger/login',p)
//乘客退出登录
export const passengerUnLogin = p => post('/passenger/unLogin',p)

//司机登录
export const driverLogin = p => post('/driver/login',p)
//司机退出登录
export const driverUnLogin = p => post('/driver/unLogin',p)

//查询登录状态的车辆
export const searchCarLogin = p =>get('/car/searchInfo',p)
//更新车辆服务状态
export const updateServiceStatus = p =>post('/car/updateServiceStatus',p)
//获取车辆介绍栏目信息
export const searchIntroduce = p => get('/car/searchIntroduce',p)



