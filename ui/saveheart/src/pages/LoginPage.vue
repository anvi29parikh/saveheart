<template>
  <q-layout>
    <q-page-container>
      <q-page style="height: 100%; min-height: 100vh;background-image: linear-gradient(135deg,#667eea,#764ba2)!important" >
          <q-card flat bordered class="fixed-center bg-white" style="height:50%; width:40%">
            <q-card-section>
              <div class="text-h6" style="text-align: center;color:#667eea">Save Heart</div>
            </q-card-section>

              <q-form autofocus id="basic-login-form" @submit="onSubmit">
                <q-card-section>
                  <label>Email*</label>
                  <q-input
                    outlined
                    dense
                    ref="userNameInput"
                    v-model="userName"
                    name="userName"
                    placeholder="Enter username"
                    :error="v$.userName.$error"
                    :error-message="errorFieldName(this.v$.userName, 'Email')"
                  />

                  <label>Password*</label>
                  <q-input
                    outlined
                    dense
                    ref="passwordInput"
                    v-model="password"
                    name="userPassword"
                    :type="isPwdVisible ? 'password' : 'text'"
                    placeholder="Enter password"
                    :error="v$.password.$error"
                    :error-message="errorFieldName(this.v$.password, 'Password')"
                  >
                    <template v-slot:append>
                      <q-icon
                        :name="isPwdVisible ? 'far fa-eye' : 'far fa-eye-slash'"
                        class="cursor-pointer"
                        style="font-size: 1.3rem"
                        @click="isPwdVisible = !isPwdVisible"
                      />
                    </template>
                  </q-input>
                </q-card-section>

                <q-card-section class="q-pt-none" style="justify-content:center;display: flex;flex-direction: column">
                  <q-btn
                    unelevated
                    no-caps
                    color="primary"
                    type="submit"
                    label="Sign In"
                  />
                  <q-btn to="/register" flat size="sm" label="Don't have an account?" color="danger" style="text-align: right"></q-btn>
                </q-card-section>

              </q-form>
          </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>
<script>
import useVuelidate from '@vuelidate/core'
import { required, email } from '@vuelidate/validators'
import { getTokenApi } from '../services/loginService'
import { errorsMixin } from '../mixins/errorsMixins'
import { CookieSet } from '../lib/cookie'
export default {
  mixins: [errorsMixin],
  name: 'LoginPage',
  setup () {
    return {
      v$: useVuelidate()
    }
  },
  data () {
    return {
      userName: undefined,
      password: undefined,
      isPwdVisible: true
    }
  },
  validations () {
    return {
      userName: {
        required,
        email
      },
      password: {
        required
      }
    }
  },
  methods: {
    onSubmit () {
      this.v$.userName.$touch()
      this.v$.password.$touch()
      if (!this.v$.$invalid) {
        const queryData = {
          email: this.userName,
          password: this.password
        }
        getTokenApi(queryData)
          .then(responeData => {
            if (responeData && responeData.data && responeData.data.token) {
              CookieSet('token', responeData.data.token)
              this.$router.push('/predictor')
              // Fetch user details
              // this.fetchUserDetails()
            } else {
              this.errorToast('Something went wrong. Please refresh the page and try again.')
            }
          }).catch(error => {
            this.handleError(error)
          })
      }
    }
  }
}
</script>
