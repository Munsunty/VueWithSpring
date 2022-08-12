<template>
  <FormKit
      type="form"
      id="registration"
      :form-class="submitted ? 'hide' : 'show'"
      submit-label="Register"
      @submit="submitHandler"
      :actions="false"
  >
    <h1>Register!</h1>

    <hr />
    <FormKit
        type="text"
        name="name"
        label="Name"
        placeholder="입력해주세요."
        validation="required"
    />
    <FormKit
        type="text"
        name="type"
        label="Type"
        placeholder="입력해주세요."
        validation="required"
    />
    <FormKit
        type="number"
        name="version"
        label="Version"
        placeholder="입력해주세요."
        validation="required"
    />

    <FormKit
        type="submit"
        label="Register"
    />

    <div v-if="submitted">

      <h2>Submission successful!</h2>
    </div>
  </FormKit>

</template>
<script>
import { reset } from '@formkit/core'
import {addCy} from "@/export/exfortVar";

export default {
  data(){
    return{
      submitted:false,
    }
  },
  methods:{
    submitHandler(data){
      this.submitted = true;
      setTimeout(()=>{
        this.submitted = false;
      },3000);
      addCy(JSON.parse(JSON.stringify(data)));
      reset('registration');


    }
  }
}


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


</style>
