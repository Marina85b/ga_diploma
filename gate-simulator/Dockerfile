FROM node:8.16.2-alpine
WORKDIR /opt/app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 9999
CMD ["npm", "start"]
