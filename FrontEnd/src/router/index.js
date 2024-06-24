import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login';
import UserList from '../components/user/UserList';
import AnalyseMain from "../components/analyse/AnalyseMain";
import ScheduleAnalyse from "../components/analyse/ScheduleAnalyse";
import DisciplineAnalyse from "../components/analyse/DisciplineAnalyse";
import UserAnalyse from "../components/analyse/UserAnalyse";
import UserMain from "../components/user/UserMain";
import UserPasswordForm from "../components/user/UserPasswordForm";
import UserPhoneForm from "@/components/user/UserPhoneForm";
import ScheduleList from "../components/meeting/ScheduleList";
import DisciplineList from "../components/meeting/DisciplineList";
import StudentMain from "../components/meeting/StudentMain";
import MeetingList from "../components/meeting/MeetingList";
import MyScheduleList from "../components/meeting/MyScheduleList";

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home,
    redirect: { path: '/home/meeting' },
    children: [
      {
        path: 'meeting',
        name: 'meeting',
        component: StudentMain,
        redirect: {path: '/home/meeting/myschedulelist'},
        children: [
          {
            path: 'myschedulelist',
            name: 'myschedulelist',
            component: MyScheduleList
          },
          {
            path: 'schedulelist',
            name: 'schedulelist',
            component: ScheduleList
          },
          {
            path: 'disciplinelist',
            name: 'disciplinelist',
            component: DisciplineList
          },
          {
            path: 'meetinglist',
            name: 'meetinglist',
            component: MeetingList
          }
        ]
      },
      {
        path: 'analyse',
        name: 'analyse',
        component: AnalyseMain,
        redirect: { path: '/home/analyse/schedule' },
        children: [
          {
            path: 'schedule',
            name: 'schedule',
            component: ScheduleAnalyse
          },
          {
            path: 'discipline',
            name: 'discipline',
            component: DisciplineAnalyse
          },
          {
            path: 'user',
            name: 'user',
            component: UserAnalyse
          }
        ]
      },
      {
        path: 'user',
        name: 'user',
        component: UserMain,
        redirect: { path: '/home/user/password' },
        children: [
          {
            path: 'password',
            name: 'password',
            component: UserPasswordForm
          },
          {
            path: 'phone',
            name: 'phone',
            component: UserPhoneForm
          },
          {
            path: 'userlist',
            name: 'userlist',
            component: UserList
          },
        ]
      }
    ]
  },
  {
    path: '/',
    name: 'login',
    component: Login,
  }
]

const router = new VueRouter({
  routes
})

export default router
