# How to create custom helm chart

ここでは spring-boot という名称のチャートを[作成](https://helm.sh/docs/helm/helm_create/)する


```shell
# helm create NAME [flags]
helm create spring-boot

# 作成結果
tree

.
├── README.md
└── spring-boot
    ├── Chart.yaml
    ├── charts
    ├── templates
    │   ├── NOTES.txt
    │   ├── _helpers.tpl
    │   ├── deployment.yaml
    │   ├── hpa.yaml
    │   ├── ingress.yaml
    │   ├── service.yaml
    │   ├── serviceaccount.yaml
    │   └── tests
    │       └── test-connection.yaml
    └── values.yaml

```

# push OCI to ACR

[How to push OCI](https://docs.microsoft.com/ja-jp/azure/container-registry/container-registry-helm-repos)


# How to deploy using helm

```shell
helm upgrade aggregator --install spring-boot/ \
    --namespace apps --create-namespace \
    --values sample.yaml 
```


# Helm registry

Helm (>= 3.8) can be stored their charts in registries (OCI supports).


## Push the helm charts on ACR `using az acr helm` command

Unfortunately, `az acr helm` is deprecated and will be removed in a future release.
Use 'helm v3' instead.

## Push the helm charts on ACR using helm


```shell

# using login user credential
PASSWORD=$(az acr login --name $ACR_NAME --expose-token --output tsv --query accessToken)


# Then you try to register login id/password to helm registry
echo -n $PASSWORD | helm registry login $ACR_NAME.azurecr.io \
  --username 00000000-0000-0000-0000-000000000000 --password-stdin

# You can see the file named 'spring-boot-app-<version>.tgz create tgz file on current folder
helm package spring-boot
helm push spring-boot-app-<ver>.tgz oci://$ACR_NAME.azurecr.io/helm

```


```shell

# deploy using oci
helm upgrade aggregator --install oci://$ACR_NAME.azurecr.io/helm/spring-boot-app \
    --version 0.1.0 \
    --namespace apps --create-namespace \
    --values sample.yaml

```
