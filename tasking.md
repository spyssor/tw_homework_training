| 序列 | 任务 | 需要时间 |
| :---:|:---:|:---:|
| 1 | 设计程序结构 | 1个小时 |

```mermaid
graph LR;
　　Portal-->|发布/更新配置|Apollo配置中心;
　　Apollo配置中心-->|实时推送|App;
　　App-->|实时查询|Apollo配置中心;
```