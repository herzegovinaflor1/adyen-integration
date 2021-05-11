FROM node:15-alpine as builder
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . .
RUN npm run build --prod

FROM nginx:1.17-alpine
COPY nginx.config /etc/nginx/conf.d/default.conf
COPY --from=builder /app/dist/payment-page /usr/share/nginx/html
EXPOSE 4200
