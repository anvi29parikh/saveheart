<template>
  <div class="bg-white">
      <div class="text-h6 text-center">Predictor</div>
      <q-card-section>
        <q-form autofocus id="basic-login-form" @submit="onSubmit">
      <q-card-section>
        <div>
        <label>Age*</label>
        <q-input
          outlined
          dense
          ref="age"
          v-model="age"
          name="age"
          type="number"
          placeholder="Enter Age"
          :error="v$.age.$error"
          :error-message="errorFieldName(this.v$.age, 'Age')"
        />
        </div>
        <div class="q-pb-sm">
        <label>Sex*</label>
        <q-radio v-model="sex" :val="0" label="Male" />
        <q-radio v-model="sex" :val="1" label="Female" />
        </div>
        <div class="q-pb-md">
        <label>Chest Pain Type*</label>
        <q-radio v-model="cp" :val="1" label="Typical Angina" />
        <q-radio v-model="cp" :val="2" label="Atypical Angina" />
        <q-radio v-model="cp" :val="3" label="Non-anginal Pain" />
        <q-radio v-model="cp" :val="4" label="Asymptomatic" />
        </div>
        <div>
        <label>Resting blood pressure (in mm Hg)*</label>
        <q-input
          outlined
          dense
          ref="trtbps"
          v-model="trtbps"
          name="trtbps"
          type="number"
          placeholder="Enter Resting blood pressure"
          :error="v$.trtbps.$error"
          :error-message="errorFieldName(this.v$.trtbps, 'Resting blood pressure')"
        />
        </div>
        <div>
        <label>Cholestoral in mg/dl fetched via BMI sensor*</label>
        <q-input
          outlined
          dense
          ref="chol"
          v-model="chol"
          name="chol"
          type="number"
          placeholder="Enter Cholestoral"
          :error="v$.chol.$error"
          :error-message="errorFieldName(this.v$.chol, 'Cholestoral')"
        />
        </div>
        <div  class="q-pb-md">
        <label>Fasting blood sugar*</label>
        <q-radio v-model="fbs" :val="1" label="> 120 mg/dl" />
        <q-radio v-model="fbs" :val="0" label="< 120 mg/dl" />
        </div>
        <div class="q-pb-md">
        <label>Resting electrocardiographic results*</label>
          <div>
        <q-radio class="row" v-model="restecg" :val="0" label="Normal" />
        <q-radio class="row" v-model="restecg" :val="1" label="Having ST-T wave abnormality (T wave inversions and/or ST elevation or depression of > 0.05 mV)" />
        <q-radio class="row" v-model="restecg" :val="2" label="Showing probable or definite left ventricular hypertrophy by Estes' criteria" />
          </div>
          </div>
        <div>
        <label>Maximum heart rate achieved*</label>
        <q-input
          outlined
          dense
          ref="thalach"
          v-model="thalach"
          name="thalach"
          type="number"
          placeholder="Enter Maximum heart rate achieved"
          :error="v$.thalach.$error"
          :error-message="errorFieldName(this.v$.thalach, 'Maximum heart rate achieved')"
        />
        </div>
        <div class="q-pb-md">
        <label>Exercise induced angina*</label>
        <q-radio v-model="exang" :val="1" label="Yes" />
        <q-radio v-model="exang" :val="0" label="No" />
        </div>
        <div>
        <label>Previous Peak*</label>
        <q-input
          outlined
          dense
          ref="oldpeak"
          v-model="oldpeak"
          name="oldpeak"
          type="number"
          placeholder="Enter Previous Peak"
          :error="v$.oldpeak.$error"
          :error-message="errorFieldName(this.v$.oldpeak, 'Previous Peak')"
        />
        </div>
        <div>
        <label>Number of major vessels*</label>
        <q-input
          outlined
          dense
          ref="ca"
          v-model="ca"
          name="ca"
          type="number"
          placeholder="Enter Number of major vessels"
          :error="v$.ca.$error"
          :error-message="errorFieldName(this.v$.ca, 'Number of major vessels')"
        />
        </div>
        <div>
        <label>Slope*</label>
        <q-input
          outlined
          dense
          ref="slope"
          v-model="slope"
          name="slope"
          type="number"
          placeholder="Enter Slope"
          :error="v$.slope.$error"
          :error-message="errorFieldName(this.v$.slope, 'Slope')"
        />
        </div>
        <div>
        <label>Thal Rate*</label>
        <q-input
          outlined
          dense
          ref="thal"
          v-model="thal"
          name="thal"
          type="number"
          placeholder="Enter Thal Rate"
          :error="v$.thal.$error"
          :error-message="errorFieldName(this.v$.thal, 'Thal Rate')"
        />
        </div>
      </q-card-section>

      <q-card-section class="q-pt-none" style="justify-content:center;display: flex;flex-direction: column">
        <q-btn
          unelevated
          no-caps
          color="primary"
          type="submit"
          label="Predict"
        />
      </q-card-section>
    </q-form>
      </q-card-section>
  </div>
</template>

<script>
import { errorsMixin } from '../mixins/errorsMixins'
import { toastMessageMixins } from 'src/mixins/toastMessageMixins'
import useVuelidate from '@vuelidate/core'
import { required } from '@vuelidate/validators'
import { getPredictionData } from '../services/userService'
export default {
  name: 'PredictorPage',
  mixins: [errorsMixin, toastMessageMixins],
  setup () {
    return {
      v$: useVuelidate()
    }
  },
  data () {
    return {
      age: undefined,
      sex: 0,
      cp: 1,
      trtbps: undefined,
      chol: undefined,
      fbs: 1,
      restecg: 0,
      thalach: undefined,
      exang: 1,
      oldpeak: undefined,
      ca: undefined,
      slope: undefined,
      thal: undefined
    }
  },
  validations () {
    return {
      age: {
        required
      },
      trtbps: {
        required
      },
      chol: {
        required
      },
      thalach: {
        required
      },
      oldpeak: {
        required
      },
      ca: {
        required
      },
      slope: {
        required
      },
      thal: {
        required
      }
    }
  },
  methods: {
    onSubmit () {
      this.v$.age.$touch()
      this.v$.trtbps.$touch()
      this.v$.chol.$touch()
      this.v$.thalach.$touch()
      this.v$.oldpeak.$touch()
      this.v$.ca.$touch()
      this.v$.slope.$touch()
      this.v$.thal.$touch()
      if (!this.v$.$invalid) {
        const queryData = {
          age: Number(this.age),
          sex: Number(this.sex),
          cp: Number(this.cp),
          trestbps: Number(this.trtbps),
          chol: Number(this.chol),
          fbs: Number(this.fbs),
          restecg: Number(this.restecg),
          thalach: Number(this.thalach),
          exang: Number(this.exang),
          oldpeak: Number(this.oldpeak),
          ca: Number(this.ca),
          slope: Number(this.slope),
          thal: Number(this.thal)
        }
        getPredictionData(queryData).then(responseData => {
          console.log('>>>>', responseData.data)
          if (responseData.data === 'Safe') {
            this.$router.push('/no-heart-disease')
          } else if (responseData.data === 'Not Safe') {
            this.$router.push('/heart-disease')
          }
        }).catch(error => {
          this.handleError(error)
        })
      }
    }
  }
}
</script>
