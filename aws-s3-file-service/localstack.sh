#!/usr/bin/env bash

aws configure set aws_access_key_id default_access_key --profile=localstack
aws configure set aws_secret_access_key default_secret_key --profile=localstack
aws configure set region us-east-1 --profile=localstack
aws configure list --profile=localstack

aws s3 mb s3://bucket1 --endpoint-url=http://localhost:4566 --profile=localstack