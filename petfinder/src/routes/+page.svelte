<script>
  // @ts-nocheck

  import { Button, Input, Label, Range, Spinner } from "flowbite-svelte";
  import { CheckCircleSolid, XCircleOutline } from "flowbite-svelte-icons";
  import { onMount } from "svelte";

  let amount = 10;
  const batchSize = 10000;

  let testing = false;

  let first = true;
  function doTest() {
    if(first) return
    console.log("Starting Test...");
    if (testing) return;
    testing = true;
    let batchBreak = false;
    let requests = [];

    while(testing)
    {
        for (let i = 0; i < amount; i++) {
    
            const element = {
                
            }
            if(i == batchSize){
                batchBreak = true;
                break;
            }
        }
        if(!batchBreak)testing = false;

        batchBreak = false;
    }

    const start = performance.now();
  }
  first = false;
</script>

<div
  class="flex flex-col gap-2.5 justify-center items-center w-screen h-screen"
>
  <Label>
    <span>Amount of Inserts</span>
    <Input type="number" class="max-w-40" bind:value={amount}></Input>
  </Label>
  <Button on:click={doTest()}>
    {#if testing}
      <Spinner></Spinner>
    {:else}
      Start Test
    {/if}
  </Button>
</div>
