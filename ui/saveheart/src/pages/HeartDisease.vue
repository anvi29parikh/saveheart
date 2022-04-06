<template>
  <div class="text-center">
   <q-img src="../assets/dangerHeart.jpeg" style="height: 5%; max-width: 20%"></q-img>
  <h6>You are at risk of developing heart disease. Please make an appointment with your doctor as soon as possible.</h6>
  <q-btn to='/predictor' label='predict again' color="primary"></q-btn>
  </div>
</template>
<script>

export default {
  name: 'heart-disease'
}
</script>
