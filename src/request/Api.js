/**   
 * api接口统一管理
 */
import { get, post } from './Http'

export const apiAddress = p => get('/user/select', p);