#!/bin/bash
#SBATCH --time=00:15:00
#SBATCH -N10
ids=$(echo $SLURM_JOB_NODELIST | grep -oP "(\d*)")
echo $ids
echo "starting with options $1 $2 $3 $4 $5"
srun -N10 -l ./selector.sh $1 $2 $3 $4 $5 $ids
