<template>
  <el-dialog :title="title" :visible.sync="visible" width="40%">
    <el-form ref="form" :model="form" :rules="rules" label-width="200px" class="demo-ruleForm">
      <el-form-item label="选择会议室" prop="meetingid">
        <el-select clearable v-model="form.meetingid" placeholder="选择会议室">
          <el-option :label="item.name" :value="item.id" v-for="item in meetings" :key="item.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始使用时间" prop="startdatetime">
        <el-date-picker
          v-model="form.startdatetime"
          type="datetime"
          format="yyyy-MM-dd HH:mm"
          value-format="yyyy-MM-dd HH:mm"
          placeholder="选择时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束使用时间" prop="enddatetime">
        <el-date-picker
          v-model="form.enddatetime"
          type="datetime"
          format="yyyy-MM-dd HH:mm"
          value-format="yyyy-MM-dd HH:mm"
          placeholder="选择时间">
        </el-date-picker>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { postAction,getAction} from '@api/manage';
import { IdentityCodeValid } from '@/utils/IdentityCodeValid'
export default {
  name: 'MyScheduleForm',
  props: {
    dialogFormVisible: Boolean,
    id:String,
    sizePerPage:Number
  },
  watch:{
    dialogFormVisible:{
      handler (newValue, oldValue) {
        this.visible=newValue;
      },
    },
    id:{
      handler (newValue, oldValue) {
        if(newValue!=null){
          this.title='编辑预定'
          getAction('schedule/detail/'+newValue, {}).then((res) => {
            this.form.id=res.data.id;
            this.form.meetingid=res.data.meetingid;
            this.form.startdatetime=res.data.startdatetime;
            this.form.enddatetime=res.data.enddatetime;
            this.form.userid=res.data.userid;
            this.form.status=res.data.status;
          });
        }else{
          this.title='新增预定'
          this.form={
            id:null,
            meetingid: undefined,
            userid: undefined,
            startdatetime: '',
            enddatetime: '',
            status: '待审'
          }
        }
      },
    },
  },
  mounted(){
    this.form={
      id:null,
      meetingid: undefined,
      userid: undefined,
      startdatetime: '',
      enddatetime: '',
      status: '待审'
    }
    this.getMeeting();
  },
  data() {
    return {
      visible:false,
      loading: false,
      title:'新增预定',
      dialogTableVisible: false,
      form: {
        id:null,
        meetingid: undefined,
        userid: undefined,
        startdatetime: '',
        enddatetime: '',
        status: '待审'
      },
      meetings:[],
      rules: {
        meetingid: [
          { required: true, message: '请选择会议室', trigger: 'change' },
          { validator: (rule, value, callback) => {
              postAction('schedule/list', {
                meetingid:this.form.meetingid,
                startscheduletime:this.form.startdatetime,
                endscheduletime:this.form.enddatetime,
                scheduletime:true,
              }).then((res) => {
                let ls=res.data.list.filter((item)=>{
                  return item.id!==this.form.id;
                });
                if(ls.length>0){
                  return callback(new Error('该会议室该时间段已被预定'));
                }else{
                  callback();
                }
              });
            }, trigger: 'change' }
        ],
        startdatetime: [
          { required: true, message: '请填写开始使用时间', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              var year = new Date().getFullYear(),
                month = new Date().getMonth()+1,
                date = new Date().getDate(),
                h = new Date().getHours(),
                m = new Date().getMinutes(),
                currentDate = year + "-" + (month < 10 ? '0' + month : month) + "-" + (date < 10 ? '0' + date : date)+" "+(h<10?'0'+h:h)+':'+(m<10?'0'+m:m);
              if(value<currentDate){
                return callback(new Error('开始使用时间不能小于当前时间'));
              }else{
                callback();
              }
            }, trigger: 'blur' },
        ],
        enddatetime: [
          { required: true, message: '请填写结束使用时间', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(value<this.form.startdatetime){
                return callback(new Error('结束使用时间不能小于开始使用时间'));
              }else{
                callback();
              }
            }, trigger: 'blur' },
        ],
      },
    };
  },
  methods:{
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.form.userid=this.$currentUser.id;
          postAction('schedule/saveSchedule', this.form).then((res) => {
            this.$message.success('保存成功')
            this.visible = false
            this.$emit('scheduleList',{currentPage:0,sizePerPage:this.sizePerPage})
          });
        } else {
          return false;
        }
      });
    },
    getMeeting(){
      postAction('meeting/getMeeting', {}).then((res) => {
        this.meetings=res.data
      });
    }
  }
}
</script>

<style scoped>

</style>
