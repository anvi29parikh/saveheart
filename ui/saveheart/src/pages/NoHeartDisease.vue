<template>
    <div class="text-center">
      <q-img src="../assets/heart.webp" style="height: 40%; max-width: 40%"></q-img>
      <h6>Congratulations! You are not at risk.However, it is always a good idea to consult with a doctor.</h6>
      <q-btn to='/predictor' label='predict again' color="primary"></q-btn>
    </div>
</template>

<script>
export default {
  name: 'NoHeartDisease'
}
</script>

<style scoped>

</style>
