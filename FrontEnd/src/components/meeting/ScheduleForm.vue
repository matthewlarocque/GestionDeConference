<template>
  <el-dialog :title="title" :visible.sync="visible" width="40%">
    <el-form ref="form" :model="form" :rules="rules" label-width="200px" class="demo-ruleForm">
      <el-form-item label="实际结束使用时间">
        <el-date-picker
          v-model="form.realitydatetime"
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
  name: 'ScheduleForm',
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
            this.form.userid=res.data.userid;
            this.form.meetingid=res.data.meetingid;
            this.form.startdatetime=res.data.startdatetime;
            this.form.enddatetime=res.data.enddatetime;
            this.form.realitydatetime=res.data.realitydatetime;
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
            realitydatetime: '',
            status: ''
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
      realitydatetime: '',
      status: ''
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
        realitydatetime: '',
        status: ''
      },
      meetings:[],
      rules: {
        enddatetime: [
          { required: true, message: '请填写实际结束使用时间', trigger: 'blur' },
          { validator: (rule, value, callback) => {
              if(value<this.form.startdatetime){
                return callback(new Error('实际结束使用时间不能小于开始使用时间'));
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
