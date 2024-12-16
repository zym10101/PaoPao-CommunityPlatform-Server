# PaoPao社区交流平台
## 1. 项目背景
### 1.1 项目功能
PaoPao是一个以用户为基础、以社区为核心、以帖子为主体的三维社区交流平台。用户能够在PaoPao中创建社区，自发管理社区，在社区里容纳用户所发表的帖子。
用户是PaoPao的基础，我们通过SaToken、Redis、MySQL等技术对用户信息进行维护，并且使用阿里云短信服务实现手机验证码功能。
社区是PaoPao的核心，我们支持用户自主创建社区，维护社区，用户对创建的社区具有管理权力，用户在社区内发布帖子作为社区内容。
帖子是PaoPao的主体，我们提供大语言模型支持帖子内容AI生成，用户可以对帖子点赞、收藏、评论。
![QQ_1725801086251](https://github.com/user-attachments/assets/7bc85eba-ff30-4929-bee5-7472a06d5781)


### 1.2 项目特色
PaoPao在项目构思初期就将百度贴吧和知识星球作为其参考竞品。
相较于贴吧，我们致力打造高质量的社区，提供清新文艺的用户界面、精简的社区环境、保护用户隐私、提高社区质量。
知识星球作为新兴的知识社区，其因为需要付费导致加入门槛较高，讨论的内容相对狭窄，缺乏生活气息。而PaoPao致力于服务更广大群体，以用户创造、用户管理为理念。
![QQ_1725801147029](https://github.com/user-attachments/assets/3f220b4e-366f-4dc8-94da-9c0116aee869)

## 2. 技术框架
### 2.1 后端架构
后端架构设计是基于Spring Cloud Alibaba开发的微服务系统，实现了高并发高可用。使用Nacos对服务进行注册和发现，通过Gateway网关对移动端的请求进行分发，使用LoadBalancer实现负载均衡，Redis对访问频率最大的用户状态信息进行缓存。使用Sentinel对流量进行控制。项目实现Docker容器化部署，更加轻量且可移植性高，允许在短时间快速部署集群。
![QQ_1725801479722](https://github.com/user-attachments/assets/e4e25821-e191-437c-bb90-a55f4a90d701)

数据存储方面我们根据业务的不同将MySQL拆分成两个数据库，分别用于用户中心和社区中心。用Redis缓存PaoPao中频繁使用的用户中心的信息，以实现快速读写和数据缓存。帖子数据量大、读写操作频繁并且数据价值较低对事务要求不高，因此使用Mongodb以文档的形式存储。将图片等大文件上传至阿里云OSS存储。
![QQ_1725801493620](https://github.com/user-attachments/assets/b4db77dc-15e3-42aa-b0c1-c3f71c520c2d)

用户中心使用Sa-Token这一轻量级的权限控制框架，相较于Spring Security和Shiro使用更便捷。同时和Redis的结合可以应对高并发高可用的场景。对于需要校验用户登录信息的服务，通过用户中心检验后再通过Openfeign远程调用帖子中心和社区中心服务。用户中心的访问量在系统中是最大的，因此通过Redis对数据缓存加快读写速度，并且即使一个节点宕机了其余节点也可以从Redis中获取用户的实时状态。
帖子中心提供帖子的发布、删除、点赞、点踩、评论、查询等功能。考虑到帖子的数据量相对较大，Mongodb的字段索引支持和内存读取可以大大加快读写速度。
社区中心提供社区创建、管理，用户加入社区和管理员审核等功能。使用HikariCP连接池和MyBatis-Plus快速开发CRUD操作。

使用Nacos对服务注册、发现、管理，并且通过Nacos控制台可以随时随地查询后台服务状态。使用Sentinel对流量进行监控和控制，在Sentinel控制台随时随地实时监控流量，配置阈值。
![QQ_1725801927120](https://github.com/user-attachments/assets/2c9c40c4-9bf5-419d-a745-4d8c7bf4ecda)
![QQ_1725801961811](https://github.com/user-attachments/assets/6c896219-31c5-4297-9284-134b570b2166)


### 2.2 前端功能
PaoPao拥有充实的界面功能：在设计之初PaoPao就希望能帮助用户创造价值，因此开发团队在开发过程中不断丰富页面内容，构思了图中的界面跳转图。秉持着聚焦用户的理念，PaoPao的移动端与页面风格都围绕用户展开，构建出了完整且饱满的移动端内容。
![QQ_1725802056973](https://github.com/user-attachments/assets/35a1f89e-5d81-474e-b8e1-8b13d0f25fbc)

PaoPao移动端的帖子详情界面内容充实，也正是这样充实的界面内容才能支持用户畅所欲言，用户在加入社区后能够自由发帖，与兴趣一致的用户一起发现美好，充实的页面也带来了良好的浏览体验，帖子与评论一体，可以用社区一同讨论生活点滴。
![QQ_1725802009376](https://github.com/user-attachments/assets/a88be148-ac95-429c-a6f1-e186dd7b8e18)

PaoPao移动端记录下了各位用户各抒己见的精彩画面，用户可以将社区帖子下的评论尽收眼底，与其他用户一同分享自己的见解，PaoPao为用户推荐了一批社区，用户可以自由挑选感兴趣的社区尽情浏览。
![QQ_1725802043176](https://github.com/user-attachments/assets/dece197a-ebf8-401d-82c2-18a08b92113e)


### 2.3 大模型接入

PaoPao的大模型接入考虑了用户、移动端页面、后端封装、文心一言模型四个维度，移动端页面是PaoPao与用户沟通的窗口，用户向PaoPao展示创意，移动端发送请求，页面提供多样化的风格选择与友好的交流界面，后端使用FastAPI封装的提示词工厂来向文心一言请求，PaoPao通过后端封装提示词工厂，相比直接请求文心一言大模型创造了更多可能性。
![QQ_1725802079440](https://github.com/user-attachments/assets/e87bd406-5644-4fbe-97b4-fdd2cf07db38)

## 3. 流程规范
### 3.1 文档齐备
PaoPao团队在开发过程中由齐备的软件文档支持，需求方案文档为团队明确方向，夯实了基础，体系结构文档帮助团队自上而下得逐步精化开发，有了这样的文档支持，小组移动端充分洞察了客户需求，实现了社区特点，小组服务端也能够精进架构，完善接口。
![QQ_1725802176739](https://github.com/user-attachments/assets/2daeff5d-782b-4f9a-8127-2e180dda6aca)

### 3.2 Git版本控制
PaoPao团队在开发过程中全程使用git做版本控制，尽管项目开发过程中内容繁多，版本树中merge操作众多，但是小组保持了良好的git使用习惯并在git中加入评审机制，拥有合理的分支管理、详细的提交信息、频繁的提交事务，可以说团队开发的版本控制良好。

![QQ_1725802192110](https://github.com/user-attachments/assets/010672ef-a4d7-41fb-89e5-23f3c3dd9860)

### 3.3 多轮迭代
PaoPao团队在开发过程中经历了多次版本迭代，项目开发的内容繁多，团队使用瀑布模型进行多轮迭代开发从需求收集到编码测试及部署，小组在几次迭代过程中的关键时间节点如下。多轮迭代帮助小组的项目精益求精，最终呈现出了百般打磨的项目。

![QQ_1725802208332](https://github.com/user-attachments/assets/7b489054-746d-4cf2-ba8a-73b93f1f41ce)


## 4. 项目部署
### 4.1 容器的构建与编排
编写Dockerfile、docker-compose.yml和配置文件，做到开发与生产环境的隔离。
![QQ_1725802271715](https://github.com/user-attachments/assets/d77ea6e3-5919-408e-8623-78119dc8ed4e)

### 4.2 部署产物
这是服务器的docker运行情况，移动端apk文件也已经制作出来，已经向部分同学发布公测。
![QQ_1725802284756](https://github.com/user-attachments/assets/762ff8de-7a03-41fb-8d5b-ac96f630992b)


## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=yesmore/inke&type=Date)](https://star-history.com/#yesmore/inke&Date)

<a href="https://www.producthunt.com/posts/inke?utm_source=badge-featured&utm_medium=badge&utm_souce=badge-inke">
  <img src="https://api.producthunt.com/widgets/embed-image/v1/featured.svg?post_id=419235&theme=light" alt="Product Hunt"/>
</a>
